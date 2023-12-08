use itertools::Itertools;

use crate::utils;

pub fn solve() -> [u64; 2] {
    let input = utils::get_input(std::module_path!());

    return [
        get_number_of_wins(&input, None),
        get_number_of_wins(&input, Some(true)),
    ];
}

fn get_number_of_wins(input: &str, is_single_race: Option<bool>) -> u64 {
    return build_races(input, is_single_race)
        .iter()
        .map(|race| race.get_number_of_wins())
        .fold(1, |acc, wins| acc * wins);
}

fn build_races(input: &str, is_single_race: Option<bool>) -> Vec<Race> {
    if is_single_race.unwrap_or(false) {
        return vec![build_single_race(input)];
    }

    return build_multiple_races(input);
}

fn build_single_race(input: &str) -> Race {
    let (times, distances) = get_times_and_distances(input);

    let time = times.iter().join("").parse().unwrap();
    let distance = distances.iter().join("").parse().unwrap();

    return Race { time, distance };
}

fn build_multiple_races(input: &str) -> Vec<Race> {
    let (times, distances) = get_times_and_distances(input);

    return times
        .iter()
        .enumerate()
        .map(|(idx, time)| Race {
            time: time.clone(),
            distance: distances[idx],
        })
        .collect_vec();
}

fn get_times_and_distances(input: &str) -> (Vec<u64>, Vec<u64>) {
    let mut lines = input.split("\n").map(|line| line.trim());

    let times = extract_values(lines.next().unwrap());
    let distances = extract_values(lines.next().unwrap());

    return (times, distances);
}

fn extract_values(line: &str) -> Vec<u64> {
    return line
        .split(":")
        .nth(1)
        .unwrap()
        .trim()
        .split(" ")
        .filter(|value_str| !value_str.is_empty())
        .map(|value_str| value_str.parse().unwrap())
        .collect_vec();
}

#[derive(Debug)]
struct Race {
    time: u64,
    distance: u64,
}

impl Race {
    // improvement/challenge: implement get first/last win using
    // binary search. this approach is performant enough but using
    // binary search to get these values would be more performant
    fn get_number_of_wins(&self) -> u64 {
        return self.get_hold_time_for_last_win() - self.get_hold_time_for_first_win() + 1;
    }

    fn get_hold_time_for_first_win(&self) -> u64 {
        for hold_time in 1..self.time {
            if self.is_win(hold_time) {
                return hold_time;
            }
        }

        return 0;
    }

    fn get_hold_time_for_last_win(&self) -> u64 {
        for hold_time in (1..self.time).rev() {
            if self.is_win(hold_time) {
                return hold_time;
            }
        }

        return 0;
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
