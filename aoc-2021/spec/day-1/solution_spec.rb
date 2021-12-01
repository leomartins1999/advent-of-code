require 'day-1/solution'

# frozen_string_literal: true

EXAMPLE = '
199
200
208
210
200
207
240
269
260
263
'.freeze

RSpec.describe Solution, 'Day 1' do
  it 'presents the expected result for the first part' do
    solution = Solution.new
    result = solution.solve_first(EXAMPLE)
    expect(result).to eq(7)
  end

  it 'presents the expected result for the second part' do
    solution = Solution.new
    result = solution.solve_second(EXAMPLE)
    expect(result).to eq(5)
  end
end
