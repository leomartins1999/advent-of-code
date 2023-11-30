pub fn solve() -> i32 {
    return get_level("tbd");
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

#[cfg(test)]
mod tests {
    use super::*;

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
