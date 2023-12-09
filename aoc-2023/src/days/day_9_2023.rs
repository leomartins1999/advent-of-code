use crate::utils;
use itertools::Itertools;

pub fn solve() -> [i64; 2] {
    let input = utils::get_input(std::module_path!());

    return [
        calculate_next_values(&input),
        calculate_previous_values(&input),
    ];
}

fn calculate_next_values(input: &str) -> i64 {
    return parse_histories(input)
        .iter()
        .map(|history| {
            let res = calculate_next_value(history);

            println!("{:?} - {res}", history);

            return res;
        })
        .sum();
}

fn calculate_previous_values(input: &str) -> i64 {
    return parse_histories(input)
        .iter()
        .map(|history| {
            let res = calculate_next_value(&history.iter().rev().map(|v| v.clone()).collect_vec());

            println!("{:?} - {res}", history);

            return res;
        })
        .sum();
}

fn parse_histories(input: &str) -> Vec<Vec<i64>> {
    return input
        .split("\n")
        .map(|line| line.trim())
        .map(|line| parse_history(&line))
        .collect();
}

fn parse_history(line: &str) -> Vec<i64> {
    return line
        .split(" ")
        .filter(|number| !number.is_empty())
        .map(|number| number.parse().unwrap())
        .collect();
}

fn calculate_next_value(history: &Vec<i64>) -> i64 {
    if history.iter().unique().count() == 1 {
        return history.first().unwrap().clone();
    }

    let delta = calculate_delta_history(history);

    println!("{:?} - {:?}", history, delta);

    return calculate_next_value(&delta) + history.last().unwrap();
}

fn calculate_delta_history(history: &Vec<i64>) -> Vec<i64> {
    let mut res = vec![];

    for i in 1..history.len() {
        let prev = history[i - 1];
        let curr = history[i];

        res.push(curr - prev);
    }

    return res;
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn part_1() {
        let input = r#"0 3 6 9 12 15
        1 3 6 10 15 21
        10 13 16 21 30 45"#;

        assert_eq!(calculate_next_values(input), 114)
    }

    #[test]
    fn part_1_example_1() {
        let input = r#"0 3 6 9 12 15"#;

        assert_eq!(calculate_next_values(input), 18)
    }

    #[test]
    fn part_2() {
        let input = r#"0 3 6 9 12 15
        1 3 6 10 15 21
        10 13 16 21 30 45"#;

        assert_eq!(calculate_previous_values(input), 2)
    }

    #[test]
    fn part_2_example_1() {
        let input = r#"0 3 6 9 12 15"#;

        assert_eq!(calculate_previous_values(input), -3)
    }
}
