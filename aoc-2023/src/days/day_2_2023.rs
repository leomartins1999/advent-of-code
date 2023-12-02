use crate::utils;

const RED_BALLS: u32 = 12;
const GREEN_BALLS: u32 = 13;
const BLUE_BALLS: u32 = 14;

pub fn solve() -> [u32; 2] {
    let input = utils::get_input(std::module_path!());

    return [sum_possible_games(&input), 0];
}

fn sum_possible_games(input: &str) -> u32 {
    return input
        .split("\n")
        .enumerate()
        .filter(|(_, line)| possible_game(line))
        .map(|(i, _)| i + 1)
        .map(|game_number| TryInto::<u32>::try_into(game_number).unwrap())
        .sum();
}

fn possible_game(input: &str) -> bool {
    return input
        .split(":")
        .nth(1)
        .unwrap()
        .trim()
        .split(";")
        .all(|round| is_round_valid(round));
}

fn is_round_valid(input: &str) -> bool {
    let (mut red, mut green, mut blue) = (0, 0, 0);

    let balls = input.split(",").map(|ball| ball.trim());

    for ball in balls {
        let mut chunks = ball.split(" ");

        let number: u32 = chunks.nth(0).unwrap().parse().unwrap();
        let color = chunks.nth(0).unwrap();

        match color {
            "red" => red += number,
            "green" => green += number,
            "blue" => blue += number,
            _ => panic!("Unknown color {color}!"),
        }

        if !is_valid(red, green, blue) {
            return false;
        }
    }

    return true;
}

fn is_valid(red: u32, green: u32, blue: u32) -> bool {
    return red <= RED_BALLS && green <= GREEN_BALLS && blue <= BLUE_BALLS;
}

#[cfg(test)]
mod test {

    use super::sum_possible_games;

    #[test]
    fn part_1_example() {
        let input = r#"Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
        Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
        Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
        Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"#;

        assert_eq!(sum_possible_games(input), 8)
    }
}
