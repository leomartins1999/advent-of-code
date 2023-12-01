use crate::utils;

const RADIX: u32 = 10;

pub fn solve() -> [u32; 2] {
    let input = utils::get_input(std::module_path!());

    return [get_calibration(&input), 1];
}

fn get_calibration(input: &str) -> u32 {
    return input.split("\n").map(|chunk| get_chunk_calibration(chunk)).sum();
}

fn get_chunk_calibration(input: &str) -> u32 {
    let digits: Vec<u32> = input
        .chars()
        .filter(|c| c.is_digit(RADIX))
        .map(|c| c.to_digit(RADIX).unwrap())
        .collect();

    return digits.first().unwrap() * 10 + digits.last().unwrap();
}

#[cfg(test)]
mod test {
    use super::get_calibration;

    #[test]
    fn part_1_example() {
        let input = r#"1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet"#;

        assert_eq!(get_calibration(input), 142)
    }
}
