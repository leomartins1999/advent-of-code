require 'day-9/solution'

# frozen_string_literal: true

EXAMPLE_DAY_9 = '
2199943210
3987894921
9856789892
8767896789
9899965678
'.freeze

RSpec.describe Day9 do
  it 'presents the expected result for the first part' do
    solution = Day9.new
    result = solution.solve_first(EXAMPLE_DAY_9)
    expect(result).to eq(15)
  end

  it 'presents the expected result for the second part' do
    solution = Day9.new
    result = solution.solve_second(EXAMPLE_DAY_9)
    expect(result).to eq(1134)
  end
end
