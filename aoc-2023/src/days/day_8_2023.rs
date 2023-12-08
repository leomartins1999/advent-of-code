use itertools::Itertools;

use crate::utils;
use std::collections::{HashMap, HashSet};

const START: &str = "AAA";

pub fn solve() -> [u64; 2] {
    let input = utils::get_input(std::module_path!());

    return [
        get_number_of_steps_to(&input, None),
        get_number_of_steps_to(&input, Some(true)),
    ];
}

fn get_number_of_steps_to(input: &str, as_ghosts: Option<bool>) -> u64 {
    let mut lines = input
        .split("\n")
        .map(|line| line.trim())
        .filter(|line| !line.is_empty());

    let directions = parse_directions(lines.next().unwrap());
    let map = build_map(lines);
    let curr_positions = get_starting_positions(as_ghosts, &map);

    return curr_positions
        .iter()
        .map(|pos| get_lowest_steps_to_destination(pos.clone(), &map, directions.clone()))
        .unique()
        .fold(1, |acc, step| num::integer::lcm(acc, step));
}

fn parse_directions<'a>(line: &str) -> DirectionIterator {
    let directions = line
        .chars()
        .map(|c| match c {
            'L' => Direction::LEFT,
            'R' => Direction::RIGHT,
            _ => panic!("Unknown direction '{c}'!"),
        })
        .collect_vec();

    return DirectionIterator::new(directions);
}

fn build_map<'a, I>(lines: I) -> HashMap<String, (String, String)>
where
    I: Iterator<Item = &'a str>,
{
    let mut map = HashMap::new();

    for (origin, destinations) in lines
        .filter(|line| !line.is_empty())
        .map(|line| parse_map_entry(line))
    {
        map.insert(origin, destinations);
    }

    return map;
}

fn parse_map_entry(line: &str) -> (String, (String, String)) {
    let mut chunks = line.split("=").map(|chunk| chunk.trim());

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

fn get_starting_positions(
    as_ghosts: Option<bool>,
    map: &HashMap<String, (String, String)>,
) -> HashSet<String> {
    if as_ghosts.unwrap_or(false) {
        let iter = map
            .keys()
            .filter(|pos| pos.ends_with("A"))
            .map(|pos| pos.clone());

        return HashSet::from_iter(iter);
    }

    return HashSet::from([START.to_string()]);
}

fn get_lowest_steps_to_destination(
    mut pos: String,
    map: &HashMap<String, (String, String)>,
    mut directions: DirectionIterator,
) -> u64 {
    let mut cnt = 0;

    while !pos.ends_with("Z") {
        let destinations = map.get(&pos).unwrap();

        pos = match directions.next().unwrap() {
            Direction::LEFT => destinations.0.clone(),
            Direction::RIGHT => destinations.1.clone(),
        };

        cnt += 1;
    }

    return cnt;
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

    fn clone(&self) -> DirectionIterator {
        return DirectionIterator {
            directions: self.directions.clone(),
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

        assert_eq!(get_number_of_steps_to(input, None), 2)
    }

    #[test]
    fn part_1_example_2() {
        let input = r#"LLR

        AAA = (BBB, BBB)
        BBB = (AAA, ZZZ)
        ZZZ = (ZZZ, ZZZ)"#;

        assert_eq!(get_number_of_steps_to(input, None), 6)
    }

    #[test]
    fn part_2_example() {
        let input = r#"LR

        11A = (11B, XXX)
        11B = (XXX, 11Z)
        11Z = (11B, XXX)
        22A = (22B, XXX)
        22B = (22C, 22C)
        22C = (22Z, 22Z)
        22Z = (22B, 22B)
        XXX = (XXX, XXX)"#;

        assert_eq!(get_number_of_steps_to(input, Some(true)), 6)
    }
}
