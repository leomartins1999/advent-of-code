require 'day-12/solution'

# frozen_string_literal: true

EXAMPLE_DAY_12_1 = '
start-A
start-b
A-c
A-b
b-d
A-end
b-end
'.freeze

EXAMPLE_DAY_12_2 = '
dc-end
HN-start
start-kj
dc-start
dc-HN
LN-dc
HN-end
kj-sa
kj-HN
kj-dc
'.freeze

EXAMPLE_DAY_12_3 = '
fs-end
he-DX
fs-he
start-DX
pj-DX
end-zg
zg-sl
zg-pj
pj-he
RW-he
fs-DX
pj-RW
zg-RW
start-pj
he-WI
zg-he
pj-fs
start-RW
'.freeze

RSpec.describe Day12 do
  it 'presents the expected result for the first part (example 1)' do
    solution = Day12.new
    result = solution.solve_first(EXAMPLE_DAY_12_1)
    expect(result).to eq(10)
  end

  it 'presents the expected result for the first part (example 2)' do
    solution = Day12.new
    result = solution.solve_first(EXAMPLE_DAY_12_2)
    expect(result).to eq(19)
  end

  it 'presents the expected result for the first part (example 3)' do
    solution = Day12.new
    result = solution.solve_first(EXAMPLE_DAY_12_3)
    expect(result).to eq(226)
  end

  it 'presents the expected result for the second part (example 1)' do
    solution = Day12.new
    result = solution.solve_second(EXAMPLE_DAY_12_1)
    expect(result).to eq(36)
  end

  it 'presents the expected result for the second part (example 2)' do
    solution = Day12.new
    result = solution.solve_second(EXAMPLE_DAY_12_2)
    expect(result).to eq(103)
  end

  it 'presents the expected result for the second part (example 3)' do
    solution = Day12.new
    result = solution.solve_second(EXAMPLE_DAY_12_3)
    expect(result).to eq(3509)
  end
end
