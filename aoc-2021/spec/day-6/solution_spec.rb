require 'day-6/solution'

# frozen_string_literal: true

EXAMPLE_DAY_6 = '
3,4,3,1,2
'.freeze

RSpec.describe Day6 do
  it 'presents the expected result for the first part (18 days)' do
    solution = Day6.new
    result = solution.solve_first(EXAMPLE_DAY_6, 18)
    expect(result).to eq(26)
  end

  it 'presents the expected result for the first part (80 days)' do
    solution = Day6.new
    result = solution.solve_first(EXAMPLE_DAY_6)
    expect(result).to eq(5934)
  end

  it 'presents the expected result for the second part' do
    solution = Day6.new
    result = solution.solve_second(EXAMPLE_DAY_6)
    expect(result).to eq(26984457539)
  end
end
