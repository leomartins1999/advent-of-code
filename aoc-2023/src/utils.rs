use std::fs;

pub fn get_input(module: &str) -> String {
    let day = module.split("::").last().unwrap();
    let file_path = format!("input/{day}.txt");

    return fs::read_to_string(file_path).unwrap();
}

#[cfg(test)]
mod tests {

    use super::get_input;

    #[test]
    fn should_be_day_1_2015() {
        assert_eq!(get_input("aoc_2023::days::input_sample"), "blah blah blah")
    }
}
