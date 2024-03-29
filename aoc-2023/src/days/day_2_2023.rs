use crate::utils;

const RED_BALLS: u32 = 12;
const GREEN_BALLS: u32 = 13;
const BLUE_BALLS: u32 = 14;

pub fn solve() -> [u32; 2] {
    let input = utils::get_input(std::module_path!());

    return [sum_possible_games(&input), games_power(&input)];
}

fn sum_possible_games(input: &str) -> u32 {
    return input
        .split("\n")
        .map(|line| build_game(line))
        .enumerate()
        .filter(|(_, game)| game.is_valid())
        .map(|(i, _)| i + 1)
        .map(|game_number| TryInto::<u32>::try_into(game_number).unwrap())
        .sum();
}

fn games_power(input: &str) -> u32 {
    return input
        .split("\n")
        .map(|line| build_game(line))
        .map(|game| game.power())
        .sum();
}

fn build_game(game_input: &str) -> Game {
    let rounds = game_input
        .split(":")
        .nth(1)
        .unwrap()
        .split(";")
        .map(|round_input| build_round(round_input))
        .collect();

    return Game { rounds };
}

fn build_round(round_input: &str) -> Balls {
    let (mut red_balls, mut green_balls, mut blue_balls) = (0, 0, 0);

    let balls = round_input.split(",").map(|ball| ball.trim());

    for ball in balls {
        let mut chunks = ball.split(" ");

        let number: u32 = chunks.nth(0).unwrap().parse().unwrap();
        let color = chunks.nth(0).unwrap();

        match color {
            "red" => red_balls += number,
            "green" => green_balls += number,
            "blue" => blue_balls += number,
            _ => panic!("Unknown color {color}!"),
        }
    }

    return Balls {
        red_balls,
        green_balls,
        blue_balls,
    };
}

struct Game {
    rounds: Vec<Balls>,
}

impl Game {
    pub fn is_valid(&self) -> bool {
        return self.rounds.iter().all(|balls| {
            balls.red_balls <= RED_BALLS
                && balls.green_balls <= GREEN_BALLS
                && balls.blue_balls <= BLUE_BALLS
        });
    }

    pub fn power(&self) -> u32 {
        let balls = self.min_balls();

        return balls.red_balls * balls.green_balls * balls.blue_balls;
    }

    fn min_balls(&self) -> Balls {
        let (mut red_balls, mut green_balls, mut blue_balls) = (0, 0, 0);

        for balls in &self.rounds {
            if red_balls < balls.red_balls {
                red_balls = balls.red_balls;
            }

            if green_balls < balls.green_balls {
                green_balls = balls.green_balls;
            }

            if blue_balls < balls.blue_balls {
                blue_balls = balls.blue_balls;
            }
        }

        return Balls {
            red_balls,
            green_balls,
            blue_balls,
        };
    }
}

struct Balls {
    red_balls: u32,
    green_balls: u32,
    blue_balls: u32,
}

#[cfg(test)]
mod test {

    use super::games_power;
    use super::sum_possible_games;

    const INPUT: &str = r#"Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
        Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
        Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
        Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"#;

    #[test]
    fn part_1_example() {
        assert_eq!(sum_possible_games(INPUT), 8)
    }

    #[test]
    fn part_2_example() {
        assert_eq!(games_power(INPUT), 2286)
    }
}
