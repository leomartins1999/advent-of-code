require 'day-2/solution'

# frozen_string_literal: true

EXAMPLE_DAY_2 = '
forward 5
down 5
forward 8
up 3
down 8
forward 2
'.freeze

RSpec.describe Day2 do
  it 'presents the expected result for the first part' do
    solution = Day2.new
    result = solution.solve_first(EXAMPLE_DAY_2)
    expect(result).to eq(150)
  end

  it 'presents the expected result for the second part' do
    solution = Day2.new
    result = solution.solve_second(EXAMPLE_DAY_2)
    expect(result).to eq(900)
  end
end
