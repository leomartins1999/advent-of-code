use crate::utils;
use itertools::Itertools;

const RADIX: u32 = 10;

const GEAR: char = '*';
const NOOP: char = '.';

const GEAR_NUMBERS: usize = 2;

pub fn solve() -> [u32; 2] {
    let input = utils::get_input(std::module_path!());

    return [sum_part_numbers(&input), sum_gear_ratios(&input)];
}

fn sum_part_numbers(input: &str) -> u32 {
    return build_schematic(input).sum_part_numbers();
}

fn sum_gear_ratios(input: &str) -> u32 {
    return build_schematic(input).sum_gear_ratios();
}

fn build_schematic(input: &str) -> EngineSchematic {
    let schema = input
        .split("\n")
        .map(|line| line.trim())
        .map(|line| line.chars().collect_vec())
        .collect_vec();

    let width = schema[0].len();
    let height = schema.len();

    return EngineSchematic {
        schema,
        width,
        height,
    };
}

struct EngineSchematic {
    schema: Vec<Vec<char>>,
    width: usize,
    height: usize,
}

#[derive(Eq, PartialEq, Hash, Clone)]
struct Position {
    x: usize,
    y: usize,
}

impl EngineSchematic {
    fn sum_part_numbers(&mut self) -> u32 {
        let mut sum = 0;

        for x in 0..self.width {
            for y in 0..self.height {
                if self.is_symbol(&Position { x, y }) {
                    sum += self
                        .get_adjacent_numbers(&Position { x, y })
                        .iter()
                        .sum::<u32>();
                }
            }
        }

        return sum;
    }

    fn sum_gear_ratios(&self) -> u32 {
        let mut sum = 0;

        for x in 0..self.width {
            for y in 0..self.height {
                if self.get_position(&Position { x, y }) == GEAR {
                    let adjacent_numbers = self.get_adjacent_numbers(&Position { x, y });

                    if adjacent_numbers.len() == GEAR_NUMBERS {
                        sum += adjacent_numbers.iter().fold(1, |sum, val| sum * val);
                    }
                }
            }
        }

        return sum;
    }

    fn get_adjacent_numbers(&self, pos: &Position) -> Vec<u32> {
        return self
            .adjacent_positions(pos)
            .iter()
            .filter(|pos| self.get_position(pos).is_digit(RADIX))
            .map(|pos| self.get_number_beggining_position(&pos))
            .unique()
            .map(|pos| self.get_number(&pos))
            .collect_vec();
    }

    fn get_number_beggining_position(&self, pos: &Position) -> Position {
        let mut x = pos.x;
        let y = pos.y;

        while x > 0 && self.get_position(&Position { x: x - 1, y }).is_digit(RADIX) {
            x -= 1;
        }

        return Position { x, y };
    }

    fn get_number(&self, beggining_pos: &Position) -> u32 {
        let mut acc = 0;

        let mut x = beggining_pos.x;
        let y = beggining_pos.y;

        while x < self.width && self.get_position(&Position { x, y }).is_digit(RADIX) {
            acc *= 10;
            acc += self.get_position_digit(&Position { x, y });
            x += 1
        }

        return acc;
    }

    fn adjacent_positions(&self, pos: &Position) -> Vec<Position> {
        let x = pos.x;
        let y = pos.y;

        let mut res = vec![];

        let starting_x = if x == 0 { 0 } else { x - 1 };
        let ending_x = if x == self.width - 1 {
            self.width - 1
        } else {
            x + 1
        };

        let starting_y = if y == 0 { 0 } else { y - 1 };
        let ending_y = if y == self.height - 1 {
            self.height - 1
        } else {
            y + 1
        };

        for other_x in starting_x..ending_x + 1 {
            for other_y in starting_y..ending_y + 1 {
                res.push(Position {
                    x: other_x,
                    y: other_y,
                })
            }
        }

        return res;
    }

    fn is_symbol(&self, pos: &Position) -> bool {
        let c = self.get(pos.x, pos.y);

        return !c.is_digit(RADIX) && c != NOOP;
    }

    fn get(&self, x: usize, y: usize) -> char {
        return self.schema[y][x];
    }

    fn get_position(&self, pos: &Position) -> char {
        return self.schema[pos.y][pos.x];
    }

    fn get_position_digit(&self, pos: &Position) -> u32 {
        return self.schema[pos.y][pos.x].to_digit(RADIX).unwrap();
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    const INPUT: &str = r#"467..114..
    ...*......
    ..35..633.
    ......#...
    617*......
    .....+.58.
    ..592.....
    ......755.
    ...$.*....
    .664.598.."#;

    #[test]
    fn part_1() {
        assert_eq!(sum_part_numbers(INPUT), 4361)
    }

    #[test]
    fn part_2() {
        assert_eq!(sum_gear_ratios(INPUT), 467835)
    }
}
