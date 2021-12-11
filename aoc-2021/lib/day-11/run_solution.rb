require_relative 'solution'

# frozen_string_literal: true

INPUT = '
3322874652
5636588857
7755117548
5854121833
2856682477
3124873812
1541372254
8634383236
2424323348
2265635842
'.freeze

solution = Day11.new

puts 'Solution for Day 11:'
puts format('---> Part 1: %s', solution.solve_first(INPUT))
puts format('---> Part 2: %s', solution.solve_second(INPUT))
