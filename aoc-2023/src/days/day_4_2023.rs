use crate::utils;
use itertools::Itertools;
use std::collections::{HashMap, HashSet};

pub fn solve() -> [u32; 2] {
    let input = utils::get_input(std::module_path!());

    return [get_points(&input), get_number_of_cards(&input)];
}

fn get_points(input: &str) -> u32 {
    return build_cards(input)
        .iter()
        .map(|card| card.get_points())
        .sum();
}

fn get_number_of_cards(input: &str) -> u32 {
    let cards = build_cards(input);

    let (number_to_card, mut card_counter) = build_card_maps(&cards);
    let last_card_number = number_to_card.len();

    for card_number in 1..=last_card_number {
        let card = number_to_card.get(&card_number).unwrap();

        let intersection_size = card.intersection_size();

        let other_starting_card = card_number + 1;
        let other_ending_card = usize::min(card_number +  intersection_size, last_card_number);

        for other_card_number in other_starting_card..=other_ending_card {
            card_counter.insert(
                other_card_number,
                card_counter.get(&other_card_number).unwrap() + card_counter.get(&card_number).unwrap(),
            );
        }
    }

    return card_counter.values().sum();
}

fn build_card_maps(cards: &Vec<Card>) -> (HashMap<usize, &Card>, HashMap<usize, u32>) {
    let mut number_to_card = HashMap::new();
    let mut card_counter = HashMap::new();

    for (i, card) in cards.iter().enumerate() {
        number_to_card.insert(i + 1, card);
        card_counter.insert(i + 1, 1);
    }

    return (number_to_card, card_counter);
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
        let intersection_size = self.intersection_size();

        if intersection_size == 0 {
            return 0;
        }

        return u32::pow(2, (intersection_size - 1).try_into().unwrap());
    }

    fn intersection_size(&self) -> usize {
        return self
            .winning_numbers
            .intersection(&self.own_numbers)
            .collect_vec()
            .len();
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
        assert_eq!(get_number_of_cards(INPUT), 30)
    }
}
