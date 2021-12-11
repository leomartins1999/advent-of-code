require 'day-11/solution'

# frozen_string_literal: true

EXAMPLE_DAY_11 = '
5483143223
2745854711
5264556173
6141336146
6357385478
4167524645
2176841721
6882881134
4846848554
5283751526
'.freeze

RSpec.describe Day11 do
  it 'presents the expected result for the first part' do
    solution = Day11.new
    result = solution.solve_first(EXAMPLE_DAY_11)
    expect(result).to eq(1656)
  end

  it 'presents the expected result for the second part' do
    solution = Day11.new
    result = solution.solve_second(EXAMPLE_DAY_11)
    expect(result).to eq(195)
  end
end
