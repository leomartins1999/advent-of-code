require 'day-13/solution'

# frozen_string_literal: true

EXAMPLE_DAY_13 = '
6,10
0,14
9,10
0,3
10,4
4,11
6,0
6,12
4,1
0,13
10,12
3,4
3,0
8,4
1,10
2,14
8,10
9,0

fold along y=7
fold along x=5
'.freeze

RSpec.describe Day13 do
  it 'presents the expected result for the first part' do
    solution = Day13.new
    result = solution.solve_first(EXAMPLE_DAY_13)
    expect(result).to eq(17)
  end

  it 'presents the expected result for the second part' do
    solution = Day13.new
    result = solution.solve_second(EXAMPLE_DAY_13)
    expect(result).to eq(nil)
  end
end
