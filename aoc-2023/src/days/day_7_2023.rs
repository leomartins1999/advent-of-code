use std::{cmp::Ordering, collections::HashMap, str::FromStr};

use crate::utils;
use itertools::Itertools;

pub fn solve() -> [u32; 2] {
    let input = utils::get_input(std::module_path!());

    return [
        get_total_winnings(&input, None),
        get_total_winnings(&input, Some(true)),
    ];
}

fn get_total_winnings(input: &str, with_joker: Option<bool>) -> u32 {
    return parse_input(input)
        .iter()
        .sorted_by(|a, b| a.compare_to(b, with_joker))
        .enumerate()
        .map(|(idx, hand)| (idx + 1, hand))
        .map(|(rank, hand)| {
            println!("Rank {rank} -> {:?}", hand);

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

#[derive(PartialEq, PartialOrd, Debug, Hash, Eq, Clone, Copy)]
enum Card {
    A,
    K,
    Q,
    T,
    _9,
    _8,
    _7,
    _6,
    _5,
    _4,
    _3,
    _2,
    J,
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

#[derive(PartialEq, PartialOrd, Ord, Eq)]
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
    fn compare_to(&self, other: &Hand, with_joker: Option<bool>) -> Ordering {
        let (self_kind, other_kind) = (self.get_kind(with_joker), other.get_kind(with_joker));

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

    fn get_kind(&self, with_joker: Option<bool>) -> HandKind {
        let tally = get_char_tally(&self.hand, None);

        if !with_joker.unwrap_or(false) || !tally.contains_key(&Card::J) || tally.len() == 1 {
            return get_kind_for_tally(tally);
        }

        let max_cnt = tally
            .iter()
            .filter(|(card, _)| **card != Card::J)
            .map(|(_, cnt)| cnt)
            .max();

        let cards_with_highest_cnt = tally
            .iter()
            .filter(|(_, cnt)| *cnt == max_cnt.unwrap())
            .map(|(card, _)| card)
            .collect_vec();

        return cards_with_highest_cnt
            .iter()
            .map(|card| get_char_tally(&self.hand, Some(**card)))
            .map(|card_tally| get_kind_for_tally(card_tally))
            .max()
            .unwrap();
    }
}

fn get_char_tally(cards: &Vec<Card>, replace_joker_with: Option<Card>) -> HashMap<Card, u32> {
    let mut res = HashMap::new();

    for c in cards.iter() {
        res.insert(*c, res.get(c).unwrap_or(&0) + 1);
    }

    println!("{:?}", res);

    if replace_joker_with.is_some() {
        let replacement = replace_joker_with.unwrap();

        res.insert(
            replacement,
            res.get(&replacement).unwrap_or(&0) + res.get(&Card::J).unwrap_or(&0),
        );
        res.remove(&Card::J);

        println!("Replacing joker with {:?}!", replacement);
        println!("{:?}", res);
    }

    return res;
}

fn get_kind_for_tally(tally: HashMap<Card, u32>) -> HandKind {
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

#[cfg(test)]
mod tests {
    use super::*;

    const INPUT_1: &str = r#"32T3K 765
    T55J5 684
    KK677 28
    KTJJT 220
    QQQJA 483"#;

    const INPUT_2: &str = r#"2345A 1
    Q2KJJ 13
    Q2Q2Q 19
    T3T3J 17
    T3Q33 11
    2345J 3
    J345A 2
    32T3K 5
    T55J5 29
    KK677 7
    KTJJT 34
    QQQJA 31
    JJJJJ 37
    JAAAA 43
    AAAAJ 59
    AAAAA 61
    2AAAA 23
    2JJJJ 53
    JJJJ2 41"#;

    #[test]
    fn part_1_input_1() {
        assert_eq!(get_total_winnings(INPUT_1, None), 6440)
    }

    #[test]
    fn part_2_input_1() {
        assert_eq!(get_total_winnings(INPUT_1, Some(true)), 5905)
    }

    #[test]
    fn part_1_input_2() {
        assert_eq!(get_total_winnings(INPUT_2, None), 6592)
    }

    #[test]
    fn part_2_input_2() {
        assert_eq!(get_total_winnings(INPUT_2, Some(true)), 6839)
    }
}
