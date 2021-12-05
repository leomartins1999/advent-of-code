require 'day-5/solution'

# frozen_string_literal: true

EXAMPLE_DAY_5 = '
0,9 -> 5,9
8,0 -> 0,8
9,4 -> 3,4
2,2 -> 2,1
7,0 -> 7,4
6,4 -> 2,0
0,9 -> 2,9
3,4 -> 1,4
0,0 -> 8,8
5,5 -> 8,2
'.freeze

RSpec.describe Day5 do
  it 'presents the expected result for the first part' do
    solution = Day5.new
    result = solution.solve_first(EXAMPLE_DAY_5)
    expect(result).to eq(5)
  end

  it 'presents the expected result for the second part' do
    solution = Day5.new
    result = solution.solve_second(EXAMPLE_DAY_5)
    expect(result).to eq(12)
  end
end
