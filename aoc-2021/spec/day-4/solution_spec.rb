require 'day-4/solution'

# frozen_string_literal: true

EXAMPLE_DAY_4_1 = '
7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1

22 13 17 11  0
8  2 23  4 24
21  9 14 16  7
6 10  3 18  5
1 12 20 15 19

3 15  0  2 22
9 18 13 17  5
19  8  7 25 23
20 11 10 24  4
14 21 16 12  6

14 21 17 24  4
10 16 15  9 19
18  8 23 26 20
22 11 13  6  5
2  0 12  3  7
'.freeze

EXAMPLE_DAY_4_2 = '
7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1

22 13 17 11  0
8  2 23  4 24
21  9 14 16  7
6 10  3 18  5
1 12 20 15 19

3 15  0  2 22
9 18 13 17  5
19  8  7 25 23
20 11 10 24  4
14 21 16 12  6

14 10 18 22  2
21 16 15  9 19
17  8 23 26 20
24 11 13  6  5
4  0 12  3  7
'.freeze

RSpec.describe Day4 do
  it 'presents the expected result for the first part (winning by line)' do
    solution = Day4.new
    result = solution.solve_first(EXAMPLE_DAY_4_1)
    expect(result).to eq(4512)
  end

  it 'presents the expected result for the first part (winning by column)' do
    solution = Day4.new
    result = solution.solve_first(EXAMPLE_DAY_4_2)
    expect(result).to eq(4512)
  end

  it 'presents the expected result for the second part (losing by line)' do
    solution = Day4.new
    result = solution.solve_second(EXAMPLE_DAY_4_1)
    expect(result).to eq(1924)
  end
end
