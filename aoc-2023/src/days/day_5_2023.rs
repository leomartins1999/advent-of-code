use itertools::Itertools;

use crate::utils;

pub fn solve() -> [u64; 2] {
    let input = utils::get_input(std::module_path!());

    return [get_lowest_location_number(&input), 0];
}

fn get_lowest_location_number(input: &str) -> u64 {
    let mut lines = input.split("\n").map(|line| line.trim()).peekable();

    let mut seeds = get_seeds(lines.next().unwrap());

    print!("{:#?}", seeds);

    while lines.peek().is_some() {
        while !lines.next().unwrap().contains("map:") {
            // noop
        }

        let mut mappings = Vec::new();

        loop {
            let line = lines.next().unwrap_or("");

            if line.is_empty() {
                break;
            }

            mappings.push(build_mapping(line))
        }

        seeds = apply_mappings(seeds, mappings);

        print!("{:#?}", seeds);
    }

    return seeds.iter().min().unwrap().to_owned();
}

fn get_seeds(seeds_line: &str) -> Vec<u64> {
    return seeds_line
        .split(":")
        .nth(1)
        .unwrap()
        .trim()
        .split(" ")
        .map(|seed_str| seed_str.parse::<u64>().unwrap())
        .collect_vec();
}

fn build_mapping(mapping_line: &str) -> Mapping {
    println!("{mapping_line}");

    let mut mapping_definition = mapping_line
        .trim()
        .split(" ")
        .map(|mappping_str| mappping_str.parse::<u64>().unwrap());

    let destination_start = mapping_definition.next().unwrap();
    let source_start = mapping_definition.next().unwrap();
    let range_length = mapping_definition.next().unwrap();

    return Mapping {
        destination_start,
        source_start,
        range_length,
    };
}

fn apply_mappings(seeds: Vec<u64>, mappings: Vec<Mapping>) -> Vec<u64> {
    return seeds
        .iter()
        .map(|seed| {
            let mapping = mappings.iter().find(|mapping| {
                let range = mapping.source_start..mapping.source_start + mapping.range_length;

                return range.contains(seed);
            });

            return match mapping {
                None => seed.to_owned(),
                Some(m) => {
                    print!("Found mapping for {seed} - {:?}", m);

                    let index = seed - m.source_start;
                    return m.destination_start + index;
                }
            };
        })
        .collect_vec();
}

#[derive(Debug)]
struct Mapping {
    destination_start: u64,
    source_start: u64,
    range_length: u64,
}

#[cfg(test)]
mod tests {
    use super::*;

    const INPUT: &str = r#"seeds: 79 14 55 13

    seed-to-soil map:
    50 98 2
    52 50 48
    
    soil-to-fertilizer map:
    0 15 37
    37 52 2
    39 0 15
    
    fertilizer-to-water map:
    49 53 8
    0 11 42
    42 0 7
    57 7 4
    
    water-to-light map:
    88 18 7
    18 25 70
    
    light-to-temperature map:
    45 77 23
    81 45 19
    68 64 13
    
    temperature-to-humidity map:
    0 69 1
    1 0 69
    
    humidity-to-location map:
    60 56 37
    56 93 4"#;

    #[test]
    fn part_1() {
        assert_eq!(get_lowest_location_number(INPUT), 35)
    }

    #[test]
    fn part_2() {
        // assert_eq!(get_lowest_location_number(INPUT), 35)
    }
}
