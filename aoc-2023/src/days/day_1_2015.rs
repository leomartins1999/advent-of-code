use std::fs;

pub fn solve() -> [i32; 2] {
    // TODO: maybe use https://doc.rust-lang.org/std/macro.module_path.html to get input path?
    let input = fs::read_to_string("./input/2015-day1-part1.txt").unwrap();

    return [get_level(&input), get_first_basement_position(&input)];
}

fn get_level(input: &str) -> i32 {
    let mut level = 0;

    for c in input.chars() {
        match c {
            '(' => level += 1,
            ')' => level -= 1,
            _ => panic!("Illegal character '{c}' at '{input}'"),
        }
    }

    return level;
}

fn get_first_basement_position(input: &str) -> i32 {
    let mut level = 0;

    for (idx, c) in input.chars().enumerate() {
        match c {
            '(' => level += 1,
            ')' => level -= 1,
            _ => panic!("Illegal character '{c}' at '{input}'"),
        }

        if level < 0 {
            return (idx + 1).try_into().unwrap();
        }
    }

    panic!("Basement not reached for '{input}'!")
}

#[cfg(test)]
mod tests {

    mod get_level {
        use super::super::get_level;

        #[test]
        fn example_1() {
            assert_eq!(get_level("(())"), 0)
        }

        #[test]
        fn example_2() {
            assert_eq!(get_level("()()"), 0)
        }

        #[test]
        fn example_3() {
            assert_eq!(get_level("((("), 3)
        }

        #[test]
        fn example_4() {
            assert_eq!(get_level("(()(()("), 3)
        }

        #[test]
        fn example_5() {
            assert_eq!(get_level("))((((("), 3)
        }

        #[test]
        fn example_6() {
            assert_eq!(get_level("())"), -1)
        }

        #[test]
        fn example_7() {
            assert_eq!(get_level("))("), -1)
        }

        #[test]
        fn example_8() {
            assert_eq!(get_level(")))"), -3)
        }

        #[test]
        fn example_9() {
            assert_eq!(get_level(")())())"), -3)
        }

        #[test]
        #[should_panic]
        fn with_invalid_input() {
            get_level("AHHHH");
        }
    }

    mod get_first_basement_position {
        use super::super::get_first_basement_position;

        #[test]
        fn example_1() {
            assert_eq!(get_first_basement_position(")"), 1)
        }

        #[test]
        fn example_2() {
            assert_eq!(get_first_basement_position("()())"), 5)
        }

        #[test]
        #[should_panic]
        fn with_invalid_input() {
            get_first_basement_position("AHHHH");
        }

        #[test]
        #[should_panic]
        fn without_reaching_basement() {
            get_first_basement_position("(((((");
        }
    }
}
