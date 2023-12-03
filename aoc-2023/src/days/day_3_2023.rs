use crate::utils;

pub fn solve() -> [u32; 2] {
    let input = utils::get_input(std::module_path!());

    return [sum_part_numbers(&input), 0];
}

fn sum_part_numbers(input: &str) -> u32 {
    return build_schematic(input).sum_part_numbers();
}

fn build_schematic(input: &str) -> EngineSchematic {
    let schema = input
        .split("\n")
        .map(|line| line.chars().collect())
        .collect();

    return EngineSchematic { schema };
}

struct EngineSchematic {
    schema: Vec<Vec<char>>,
}

impl EngineSchematic {
    fn sum_part_numbers(&self) -> u32 {
        let mut part_numbers = 0;

        for (y, line) in self.schema.iter().enumerate() {
            for (x, _column) in line.iter().enumerate() {
                let c = self.get(x, y);

                if is_symbol(c) {
                    part_numbers += self.adjacent_part_numbers(x, y).iter().sum::<u32>();
                }
            }
        }

        return part_numbers;
    }

    fn get(&self, x: usize, y: usize) -> char {
        return self.schema[y][x];
    }

    fn adjacent_part_numbers(&self, x: usize, y: usize) -> Vec<u32> {
        
        
        return vec![0];
    }
}

fn is_symbol(c: char) -> bool {
    return !c.is_alphanumeric() && c != '.';
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
