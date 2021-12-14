require_relative 'polymer_simulator'

# Solution for day 14 of Advent of Code 2021
class Day14
  def initialize
    @simulator = PolymerSimulator.new
  end

  def solve_first(input, steps = 10)
    initial_sequence, pair_mapper = read_input(input)

    res = @simulator.simulate(initial_sequence, pair_mapper, steps)

    @simulator.get_score(res, initial_sequence[-1])
  end

  def solve_second(input)
    solve_first(input, 40)
  end

  private

  def read_input(input)
    lines = input
      .split(/\n+/)
      .reject(&:empty?)

    [
      lines[0],
      read_pair_mappers(lines.drop(1))
    ]
  end

  def read_pair_mappers(lines)
    lines
      .map { |l| l.split('->') }
      .map { |l| [l[0].strip, l[1].strip] }
      .reduce({}) {|acc, mapping| acc.merge({mapping.first => mapping.last}) }
  end
end
