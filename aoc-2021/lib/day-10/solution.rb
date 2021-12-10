require_relative 'line_interpreter'

# Solution for day 10 of Advent of Code 2021
class Day10
  def initialize
    @checker = LineInterpreter.new
  end

  def solve_first(input)
    lines = parse_input(input)

    @checker.get_syntax_score(lines)
  end

  def solve_second(input)
    lines = parse_input(input)

    @checker.get_autocomplete_score(lines)
  end

  private

  def parse_input(input)
    input
      .split(/\n+/)
      .reject(&:empty?)
      .map { |l| l.split('') }
  end
end
