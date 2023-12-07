use std::{cmp::Ordering, collections::HashMap, str::FromStr};

use crate::utils;
use itertools::Itertools;

pub fn solve() -> [u32; 2] {
    let input = utils::get_input(std::module_path!());

    return [get_total_winnings(&input), 0];
}

fn get_total_winnings(input: &str) -> u32 {
    return parse_input(input)
        .iter()
        .sorted_by(|a, b| a.compare_to(b))
        .enumerate()
        .map(|(idx, hand)| (idx + 1, hand))
        .map(|(rank, hand)| {
            // println!("Rank {rank} -> {:?}", hand);

            return TryInto::<u32>::try_into(rank).unwrap() * hand.bid;
        })
        .sum();
}

fn parse_input(input: &str) -> Vec<Hand> {
    return input
        .split("\n")
        .map(|hand_input| hand_input.trim())
        .map(|hand_input| build_hand_and_bid(hand_input))
        .collect_vec();
}

fn build_hand_and_bid(hand_input: &str) -> Hand {
    let mut parts = hand_input.split(" ").map(|part| part.trim());

    let hand = parts
        .next()
        .unwrap()
        .chars()
        .map(|c| Card::from_str(&c.to_string()).unwrap())
        .collect_vec();

    let bid: u32 = parts.next().unwrap().parse().unwrap();

    return Hand { hand, bid };
}

#[derive(Debug)]
struct Hand {
    hand: Vec<Card>,
    bid: u32,
}

#[derive(PartialEq, PartialOrd, Debug, Hash, Eq, Copy, Clone)]
enum Card {
    A,
    K,
    Q,
    J,
    T,
    _9,
    _8,
    _7,
    _6,
    _5,
    _4,
    _3,
    _2,
}

impl FromStr for Card {
    type Err = ();

    fn from_str(s: &str) -> Result<Self, Self::Err> {
        match s {
            "A" => Ok(Card::A),
            "K" => Ok(Card::K),
            "Q" => Ok(Card::Q),
            "J" => Ok(Card::J),
            "T" => Ok(Card::T),
            "9" => Ok(Card::_9),
            "8" => Ok(Card::_8),
            "7" => Ok(Card::_7),
            "6" => Ok(Card::_6),
            "5" => Ok(Card::_5),
            "4" => Ok(Card::_4),
            "3" => Ok(Card::_3),
            "2" => Ok(Card::_2),
            _ => Err(()),
        }
    }
}

#[derive(PartialEq, PartialOrd)]
enum HandKind {
    HighCard,
    OnePair,
    TwoPair,
    ThreeOfAKind,
    FullHouse,
    FourOfAKind,
    FiveOfAKind,
}

impl Hand {
    fn compare_to(&self, other: &Hand) -> Ordering {
        let (self_kind, other_kind) = (self.get_kind(), other.get_kind());

        if self_kind > other_kind {
            return Ordering::Greater;
        } else if other_kind > self_kind {
            return Ordering::Less;
        }

        if self.hand < other.hand {
            return Ordering::Greater;
        } else if other.hand < self.hand {
            return Ordering::Less;
        }

        return Ordering::Equal;
    }

    fn get_kind(&self) -> HandKind {
        let tally = get_char_tally(&self.hand);

        if tally.len() == 1 {
            return HandKind::FiveOfAKind;
        }

        if tally.values().max().unwrap() == &4 {
            return HandKind::FourOfAKind;
        }

        if tally.values().contains(&3) {
            if tally.values().contains(&2) {
                return HandKind::FullHouse;
            }

            return HandKind::ThreeOfAKind;
        }

        if tally.values().contains(&2) {
            if tally.values().filter(|cnt| *cnt == &2).count() == 2 {
                return HandKind::TwoPair;
            }

            return HandKind::OnePair;
        }

        return HandKind::HighCard;
    }
}

fn get_char_tally(cards: &Vec<Card>) -> HashMap<Card, u32> {
    let mut res = HashMap::new();

    for c in cards.iter() {
        res.insert(*c, res.get(c).unwrap_or(&0) + 1);
    }

    return res;
}

#[cfg(test)]
mod tests {
    use super::*;

    const INPUT: &str = r#"32T3K 765
    T55J5 684
    KK677 28
    KTJJT 220
    QQQJA 483"#;

    #[test]
    fn part_1() {
        assert_eq!(get_total_winnings(INPUT), 6440)
    }

    #[test]
    fn part_2() {
        //assert_eq!(get_number_of_cards(INPUT), 30)
    }
}
