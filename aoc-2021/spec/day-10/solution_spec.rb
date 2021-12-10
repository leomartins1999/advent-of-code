require 'day-10/solution'

# frozen_string_literal: true

EXAMPLE_DAY_10 = '
[({(<(())[]>[[{[]{<()<>>
[(()[<>])]({[<{<<[]>>(
{([(<{}[<>[]}>{[]{[(<()>
(((({<>}<{<{<>}{[]{[]{}
[[<[([]))<([[{}[[()]]]
[{[{({}]{}}([{[{{{}}([]
{<[[]]>}<{[{[{[]{()[[[]
[<(<(<(<{}))><([]([]()
<{([([[(<>()){}]>(<<{{
<{([{{}}[<[[[<>{}]]]>[]]
'.freeze

RSpec.describe Day10 do
  it 'presents the expected result for the first part' do
    solution = Day10.new
    result = solution.solve_first(EXAMPLE_DAY_10)
    expect(result).to eq(26397)
  end

  it 'presents the expected result for the second part' do
    solution = Day10.new
    result = solution.solve_second(EXAMPLE_DAY_10)
    expect(result).to eq(288957)
  end
end
