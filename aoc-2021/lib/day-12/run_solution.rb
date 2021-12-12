require_relative 'solution'

# frozen_string_literal: true

INPUT = '
xz-end
CJ-pt
pt-QW
hn-SP
pw-CJ
SP-end
hn-pt
GK-nj
fe-nj
CJ-nj
hn-ZZ
hn-start
hn-fe
ZZ-fe
SP-nj
SP-xz
ZZ-pt
nj-ZZ
start-ZZ
hn-GK
CJ-end
start-fe
CJ-xz
'.freeze

solution = Day12.new

puts 'Solution for Day 12:'
puts format('---> Part 1: %s', solution.solve_first(INPUT))
puts format('---> Part 2: %s', solution.solve_second(INPUT))
