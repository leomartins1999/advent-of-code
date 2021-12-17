require 'day-15/solution'

# frozen_string_literal: true

EXAMPLE_DAY_15_1 = '
1163751742
1381373672
2136511328
3694931569
7463417111
1319128137
1359912421
3125421639
1293138521
2311944581
'.freeze

EXAMPLE_DAY_15_2 = '
19999
19111
11191
'.freeze

RSpec.describe Day15 do
  it 'presents the expected result for the first part (example 1)' do
    solution = Day15.new
    result = solution.solve_first(EXAMPLE_DAY_15_1)
    expect(result).to eq(40)
  end

  it 'presents the expected result for the first part (example 2)' do
    solution = Day15.new
    result = solution.solve_first(EXAMPLE_DAY_15_2)
    expect(result).to eq(8)
  end

  it 'presents the expected result for the second part' do
    solution = Day15.new
    result = solution.solve_second(EXAMPLE_DAY_15_1)
    expect(result).to eq(315)
  end
end
