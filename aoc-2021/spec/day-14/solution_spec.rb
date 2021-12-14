require 'day-14/solution'

# frozen_string_literal: true

EXAMPLE_DAY_14 = '
NNCB

CH -> B
HH -> N
CB -> H
NH -> C
HB -> C
HC -> B
HN -> C
NN -> C
BH -> H
NC -> B
NB -> B
BN -> B
BB -> N
BC -> B
CC -> N
CN -> C
'.freeze

RSpec.describe Day14 do
  it 'presents the expected result for the first part (10 steps)' do
    solution = Day14.new
    result = solution.solve_first(EXAMPLE_DAY_14)
    expect(result).to eq(1588)
  end

  it 'presents the expected result for the second part' do
    solution = Day14.new
    result = solution.solve_second(EXAMPLE_DAY_14)
    expect(result).to eq(2188189693529)
  end
end
