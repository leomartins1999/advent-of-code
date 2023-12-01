use crate::utils;

const RADIX: u32 = 10;
const TEXT_TO_DIGIT: [(&str, &str); 9] = [
    ("one", "1"),
    ("two", "2"),
    ("three", "3"),
    ("four", "4"),
    ("five", "5"),
    ("six", "6"),
    ("seven", "7"),
    ("eight", "8"),
    ("nine", "9"),
];

pub fn solve() -> [u32; 2] {
    let input = utils::get_input(std::module_path!());

    return [
        get_calibration(input.clone(), None),
        get_calibration(input.clone(), Some(true)),
    ];
}

fn get_calibration(mut input: String, with_text_digits: Option<bool>) -> u32 {
    if with_text_digits.unwrap_or(false) {
        for mapping in TEXT_TO_DIGIT {
            let (text, digit) = mapping;

            let beginning = text.chars().nth(0).unwrap().to_string();
            let termination = text.chars().last().unwrap().to_string();

            input = input.replace(text, format!("{beginning}{digit}{termination}").as_str());
        }
    }

    return input
        .split("\n")
        .map(|chunk| get_chunk_calibration(chunk))
        .sum();
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
        treb7uchet"#
            .to_string();

        assert_eq!(get_calibration(input, None), 142)
    }

    #[test]
    fn part_2_example() {
        let input = r#"two1nine
        eightwothree
        abcone2threexyz
        xtwone3four
        4nineeightseven2
        zoneight234
        7pqrstsixteen"#
            .to_string();

        assert_eq!(get_calibration(input, Some(true)), 281)
    }
}
