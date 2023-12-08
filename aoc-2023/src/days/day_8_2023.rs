use itertools::Itertools;

use crate::utils;
use std::{
    collections::HashMap,
    iter::{self, FlatMap},
    str::Chars,
};

const START: &str = "AAA";
const DESTINATION: &str = "ZZZ";

pub fn solve() -> [u32; 2] {
    let input = utils::get_input(std::module_path!());

    return [get_number_of_steps_to(&input, DESTINATION), 0];
}

fn get_number_of_steps_to(input: &str, destination: &str) -> u32 {
    let mut lines = input
        .split("\n")
        .map(|line| line.trim())
        .filter(|line| !line.is_empty());

    let mut directions = parse_directions(lines.next().unwrap());

    let mut map = Map::new();

    let mut curr = START.to_string();
    let mut cnt = 0;

    while curr != destination {
        while !&map.contains(&curr) {
            let (key, destinations) = parse_map_entry(lines.next().unwrap());
            map.set(key, destinations)
        }

        let destinations = map.get(&curr);

        curr = match directions.next().unwrap() {
            Direction::LEFT => destinations.0,
            Direction::RIGHT => destinations.1,
        };
        cnt += 1;
    }

    return cnt;
}

fn parse_directions<'a>(line: &str) -> DirectionIterator {
    let directions = line
        .chars()
        .map(|c| match c {
            'L' => Direction::LEFT,
            'R' => Direction::RIGHT,
            _ => panic!("Unknown direction!"),
        })
        .collect_vec();

    return DirectionIterator::new(directions);
}

fn parse_map_entry(entry_line: &str) -> (String, (String, String)) {
    let mut chunks = entry_line.split("=").map(|chunk| chunk.trim());

    let key = chunks.next().unwrap();

    let sanitized_chunks = chunks.next().unwrap().replace("(", "").replace(")", "");

    let mut destinations = sanitized_chunks.split(",").map(|chunk| chunk.trim());

    let left_destination = destinations.next().unwrap();
    let right_destination = destinations.next().unwrap();

    return (
        key.to_string(),
        (left_destination.to_string(), right_destination.to_string()),
    );
}

struct DirectionIterator {
    directions: Vec<Direction>,
    curr: usize,
}

impl DirectionIterator {
    fn new(directions: Vec<Direction>) -> DirectionIterator {
        return DirectionIterator {
            directions,
            curr: 0,
        };
    }
}

impl Iterator for DirectionIterator {
    type Item = Direction;

    fn next(&mut self) -> Option<Self::Item> {
        let res = &self.directions[self.curr];

        if self.curr == self.directions.len() - 1 {
            self.curr = 0;
        } else {
            self.curr += 1;
        }

        return Some(res.clone());
    }
}

struct Map {
    map: HashMap<String, (String, String)>,
}

impl Map {
    fn new() -> Map {
        return Map {
            map: HashMap::new(),
        };
    }

    fn contains(&mut self, key: &str) -> bool {
        return self.map.contains_key(key);
    }

    fn get(&mut self, key: &str) -> (String, String) {
        return self.map.get(key).unwrap().clone();
    }

    fn set(&mut self, key: String, destination: (String, String)) {
        self.map.insert(key, destination);
    }
}

#[derive(Clone)]
enum Direction {
    LEFT,
    RIGHT,
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn part_1_example_1() {
        let input = r#"RL

        AAA = (BBB, CCC)
        BBB = (DDD, EEE)
        CCC = (ZZZ, GGG)
        DDD = (DDD, DDD)
        EEE = (EEE, EEE)
        GGG = (GGG, GGG)
        ZZZ = (ZZZ, ZZZ)"#;

        assert_eq!(get_number_of_steps_to(input, "ZZZ"), 2)
    }

    #[test]
    fn part_1_example_2() {
        let input = r#"LLR

        AAA = (BBB, BBB)
        BBB = (AAA, ZZZ)
        ZZZ = (ZZZ, ZZZ)"#;

        assert_eq!(get_number_of_steps_to(input, "ZZZ"), 6)
    }

    #[test]
    fn part_2() {
        // assert_eq!(get_number_of_wins(INPUT, Some(true)), 71503)
    }
}
