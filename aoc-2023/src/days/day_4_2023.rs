use crate::utils;
use itertools::Itertools;
use std::collections::HashSet;

pub fn solve() -> [u32; 2] {
    let input = utils::get_input(std::module_path!());

    return [get_points(&input), 0];
}

fn get_points(input: &str) -> u32 {
    return build_cards(input)
        .iter()
        .map(|card| card.get_points())
        .sum();
}

fn build_cards(input: &str) -> Vec<Card> {
    return input
        .split("\n")
        .map(|card_line| build_card(card_line))
        .collect_vec();
}

fn build_card(card_input: &str) -> Card {
    let mut numbers = card_input.split(":").nth(1).unwrap().split("|");

    let winning_numbers = parse_numbers(numbers.nth(0).unwrap());
    let own_numbers = parse_numbers(numbers.nth(0).unwrap());

    return Card {
        winning_numbers,
        own_numbers,
    };
}

fn parse_numbers(input: &str) -> HashSet<u32> {
    let numbers = input
        .trim()
        .split(" ")
        .filter(|number_str| !number_str.is_empty())
        .map(|number_str| number_str.parse().unwrap());

    return HashSet::from_iter(numbers);
}

struct Card {
    winning_numbers: HashSet<u32>,
    own_numbers: HashSet<u32>,
}

impl Card {
    fn get_points(&self) -> u32 {
        let intersection_size = self
            .winning_numbers
            .intersection(&self.own_numbers)
            .collect_vec()
            .len();

        if intersection_size == 0 {
            return 0;
        }

        return u32::pow(2, (intersection_size - 1).try_into().unwrap());
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    const INPUT: &str = r#"Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
    Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
    Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
    Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
    Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
    Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"#;

    #[test]
    fn part_1() {
        assert_eq!(get_points(INPUT), 13)
    }

    #[test]
    fn part_2() {
        // assert_eq!(sum_gear_ratios(INPUT), 467835)
    }
}
