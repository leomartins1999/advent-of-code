require 'day-3/solution'

# frozen_string_literal: true

EXAMPLE_DAY_3 = '
00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010
'.freeze

RSpec.describe Day3 do
  it 'presents the expected result for the first part' do
    solution = Day3.new
    result = solution.solve_first(EXAMPLE_DAY_3)
    expect(result).to eq(198)
  end

  it 'presents the expected result for the second part' do
    solution = Day3.new
    result = solution.solve_second(EXAMPLE_DAY_3)
    expect(result).to eq(230)
  end
end
