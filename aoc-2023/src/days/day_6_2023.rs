use itertools::Itertools;

use crate::utils;

pub fn solve() -> [u64; 2] {
    let input = utils::get_input(std::module_path!());

    return [
        get_number_of_wins(&input, None),
        get_number_of_wins(&input, Some(true)),
    ];
}

fn get_number_of_wins(input: &str, single_race: Option<bool>) -> u64 {
    return build_races(input, single_race)
        .iter()
        .map(|race| race.get_number_of_wins())
        .fold(1, |acc, wins| acc * wins);
}

fn build_races(input: &str, single_race: Option<bool>) -> Vec<Race> {
    let mut lines = input.split("\n").map(|line| line.trim());

    let times = extract_values(lines.next().unwrap());
    let distances = extract_values(lines.next().unwrap());

    if single_race.unwrap_or(false) {
        let time = times.iter().join("").parse().unwrap();
        let distance = distances.iter().join("").parse().unwrap();

        return vec![Race { time, distance }];
    } else {
        return times
            .iter()
            .enumerate()
            .map(|(idx, time)| Race {
                time: time.clone(),
                distance: distances[idx],
            })
            .collect_vec();
    }
}

fn extract_values(line: &str) -> Vec<u64> {
    return line
        .split(":")
        .skip(1)
        .next()
        .unwrap()
        .trim()
        .split(" ")
        .filter(|value_str| !value_str.is_empty())
        .map(|value_str| {
            println!("{line} - {value_str}");

            return value_str.parse().unwrap();
        })
        .collect_vec();
}

#[derive(Debug)]
struct Race {
    time: u64,
    distance: u64,
}

impl Race {
    fn get_number_of_wins(&self) -> u64 {
        println!("Race {:?}", self);

        let mut win_counter = 0;
        let mut won_at_least_once = false;

        for hold_time in 1..self.time {
            let won = self.is_win(hold_time);

            println!("-> Won with {hold_time} hold? {won}");

            if won {
                win_counter += 1;
                won_at_least_once = true;
            } else if won_at_least_once {
                break;
            }
        }

        return win_counter;
    }

    fn is_win(&self, hold_time: u64) -> bool {
        let remaining_time = self.time - hold_time;

        return self.distance < remaining_time * hold_time;
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    const INPUT: &str = r#"Time:      7  15   30
    Distance:  9  40  200"#;

    #[test]
    fn part_1() {
        assert_eq!(get_number_of_wins(INPUT, None), 288)
    }

    #[test]
    fn part_2() {
        assert_eq!(get_number_of_wins(INPUT, Some(true)), 71503)
    }
}
