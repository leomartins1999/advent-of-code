require 'day-7/solution'

# frozen_string_literal: true

EXAMPLE_DAY_7 = '
16,1,2,0,4,2,7,1,2,14
'.freeze

RSpec.describe Day7 do
  it 'presents the expected result for the first part' do
    solution = Day7.new
    result = solution.solve_first(EXAMPLE_DAY_7)
    expect(result).to eq(37)
  end

  it 'presents the expected result for the second part' do
    solution = Day7.new
    result = solution.solve_second(EXAMPLE_DAY_7)
    expect(result).to eq(168)
  end
end
