use crate::utils;

const RADIX: u32 = 10;

pub fn solve() -> [u32; 2] {
    let input = utils::get_input(std::module_path!());

    return [sum_part_numbers(&input), 0];
}

fn sum_part_numbers(input: &str) -> u32 {
    return build_schematic(input).sum_part_numbers();
}

fn build_schematic(input: &str) -> EngineSchematic {
    let schema: Vec<Vec<char>> = input
        .split("\n")
        .map(|line| line.trim())
        .map(|line| line.chars().collect())
        .collect();

    let width = schema[0].len();
    let height = schema.len();

    return EngineSchematic {
        schema,
        width,
        height,
    };
}

#[derive(Debug)]
struct EngineSchematic {
    schema: Vec<Vec<char>>,
    width: usize,
    height: usize,
}

#[derive(PartialEq)]
enum Direction {
    Left,
    Right,
    Both,
}

impl EngineSchematic {
    fn sum_part_numbers(&mut self) -> u32 {
        self.filter_non_part_numbers();

        return self.sum_numbers();
    }

    fn filter_non_part_numbers(&mut self) {
        for x in 0..self.width {
            for y in 0..self.height {
                if self.get(x, y).is_digit(RADIX) && !self.is_part_number(x, y, None) {
                    self.remove(x, y)
                }
            }
        }
    }

    fn is_part_number(&self, x: usize, y: usize, direction: Option<Direction>) -> bool {
        if !self.get(x, y).is_digit(RADIX) {
            return false;
        }

        let recursive_direction = direction.unwrap_or(Direction::Both);

        println!(
            "{x}{y} - {}, {}, {}",
            self.get(x, y),
            self.is_adjacent_to_symbol(x, y),
            self.get(x, y).is_digit(RADIX)
        );

        if self.is_adjacent_to_symbol(x, y) {
            return true;
        }

        if recursive_direction != Direction::Right
            && x > 0
            && self.is_part_number(x - 1, y, Some(Direction::Left))
        {
            return true;
        }

        if recursive_direction != Direction::Left
            && x < self.width - 1
            && self.is_part_number(x + 1, y, Some(Direction::Right))
        {
            return true;
        }

        return false;
    }

    fn is_adjacent_to_symbol(&self, x: usize, y: usize) -> bool {
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
                if self.is_symbol(other_x, other_y) {
                    return true;
                }
            }
        }

        return false;
    }

    fn sum_numbers(&self) -> u32 {
        return self
            .schema
            .iter()
            .map(|line| {
                line.iter()
                    .map(|char| if char.is_digit(RADIX) { char } else { &'.' })
                    .map(|char| char.to_owned())
                    .collect::<Vec<char>>()
            })
            .map(|line| String::from_iter(line))
            .flat_map(|line| {
                line.split(".")
                    .map(|str| str.to_owned())
                    .collect::<Vec<String>>()
            })
            .map(|number_str| {
                number_str
                    .chars()
                    .filter(|c| c.is_digit(RADIX))
                    .collect::<Vec<char>>()
            })
            .filter(|number_chars| number_chars.len() > 0)
            .map(|number_chars| String::from_iter(number_chars))
            .map(|number_str| number_str.parse::<u32>().unwrap())
            .map(|number| {
                println!("{number}");
                number
            })
            .sum();
    }

    fn is_symbol(&self, x: usize, y: usize) -> bool {
        let c = self.get(x, y);

        return !c.is_digit(RADIX) && c != '.';
    }

    fn get(&self, x: usize, y: usize) -> char {
        return self.schema[y][x];
    }

    fn remove(&mut self, x: usize, y: usize) {
        self.schema[y][x] = '.';
    }
}

#[cfg(test)]
mod tests {
    use super::sum_part_numbers;

    #[test]
    fn example_1() {
        let input = r#"467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...$.*....
        .664.598.."#;

        assert_eq!(sum_part_numbers(input), 4361)
    }
}
