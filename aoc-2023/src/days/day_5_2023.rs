use crate::utils;
use itertools::Itertools;

pub fn solve() -> [u64; 2] {
    let input = utils::get_input(std::module_path!());

    return [
        get_lowest_location(&input, None),
        get_lowest_location(&input, Some(true)),
    ];
}

fn get_lowest_location(input: &str, with_seed_ranges: Option<bool>) -> u64 {
    let mut lines = input.split("\n").map(|line| line.trim());

    let seed_ranges = parse_seed_ranges(lines.next().unwrap(), with_seed_ranges);

    let maps = parse_maps(lines);

    return apply_maps_to_ranges(maps, seed_ranges)
        .iter()
        .map(|range| range.start)
        .min()
        .unwrap();
}

fn parse_seed_ranges(seeds_line: &str, with_seed_ranges: Option<bool>) -> Vec<Range> {
    if with_seed_ranges.unwrap_or(false) {
        return build_seed_ranges(seeds_line);
    }

    return build_single_seed_ranges(seeds_line);
}

fn build_seed_ranges(seeds_line: &str) -> Vec<Range> {
    return get_seeds_data(seeds_line)
        .chunks(2)
        .map(|chunk| {
            let mut iter = chunk.iter();

            let start = iter.next().unwrap().to_owned();
            let length = iter.next().unwrap();
            let end = start + length - 1;

            return Range { start, end };
        })
        .collect_vec();
}

fn build_single_seed_ranges(seeds_line: &str) -> Vec<Range> {
    return get_seeds_data(seeds_line)
        .iter()
        .map(|start| Range {
            start: start.to_owned(),
            end: start + 1,
        })
        .collect_vec();
}

fn get_seeds_data(seeds_line: &str) -> Vec<u64> {
    return seeds_line
        .split(":")
        .nth(1)
        .unwrap()
        .split(" ")
        .filter(|seed_str| !seed_str.is_empty())
        .map(|seed_str| seed_str.trim().parse().unwrap())
        .collect_vec();
}

fn parse_maps<'a, I>(lines: I) -> Vec<Map>
where
    I: Iterator<Item = &'a str>,
{
    let mut maps = vec![];

    let mut iter = lines.peekable();

    while iter.peek().is_some() {
        while !iter.next().unwrap().contains("map:") {
            // noop
        }

        let mut entries = vec![];
        loop {
            let line = iter.next().unwrap_or("");

            if line.is_empty() {
                break;
            }

            entries.push(parse_range(line))
        }

        maps.push(Map { entries });
    }

    return maps;
}

fn parse_range(line: &str) -> MapEntry {
    let mut parts = line.split(" ");

    let destination_start = parts.next().unwrap().parse().unwrap();
    let source_start = parts.next().unwrap().parse().unwrap();
    let length: u64 = parts.next().unwrap().parse().unwrap();

    let source_end = source_start + length - 1;

    return MapEntry {
        destination_start,
        source_start,
        source_end,
    };
}

fn apply_maps_to_ranges(maps: Vec<Map>, ranges: Vec<Range>) -> Vec<Range> {
    return maps
        .iter()
        .fold(ranges, |ranges, map| apply_map_to_ranges(map, ranges));
}

fn apply_map_to_ranges(map: &Map, ranges: Vec<Range>) -> Vec<Range> {
    return ranges
        .iter()
        .flat_map(|range| apply_map_to_range(map, range))
        .collect_vec();
}

fn apply_map_to_range(map: &Map, range: &Range) -> Vec<Range> {
    let ranges = map
        .entries
        .iter()
        .filter(|entry| is_entry_applicable_to_range(entry, range))
        .map(|entry| apply_entry_to_range(entry, range))
        .collect_vec();

    if ranges.is_empty() {
        return vec![range.clone()];
    }

    return ranges;
}

fn is_entry_applicable_to_range(entry: &MapEntry, range: &Range) -> bool {
    return range.start <= entry.source_end && range.end > entry.source_start;
}

fn apply_entry_to_range(entry: &MapEntry, range: &Range) -> Range {
    let intersection_start = u64::max(range.start, entry.source_start);
    let intersection_end = u64::min(range.end, entry.source_end);

    let start = intersection_start - entry.source_start + entry.destination_start;
    let end = intersection_end - entry.source_start + entry.destination_start;

    return Range { start, end };
}

#[derive(Copy, Clone)]
struct Range {
    start: u64,
    end: u64, // inclusive
}

struct Map {
    entries: Vec<MapEntry>,
}

struct MapEntry {
    source_start: u64,
    source_end: u64,
    destination_start: u64, // inclusive
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
        assert_eq!(get_lowest_location(INPUT, None), 35)
    }

    #[test]
    fn part_2() {
        assert_eq!(get_lowest_location(INPUT, Some(true)), 46)
    }
}
