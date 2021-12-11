require_relative 'octopus_herd'

# Solution for day 10 of Advent of Code 2021
class Day11

  def solve_first(input)
    octopuses = parse_input(input)

    herd = OctopusHerd.new(octopuses)

    herd.get_number_of_flashes
  end

  def solve_second(input)
    octopuses = parse_input(input)

    herd = OctopusHerd.new(octopuses)

    herd.get_day_for_all_octopuses
  end

  private

  def parse_input(input)
    input
      .split(/\n+/)
      .reject(&:empty?)
      .map { |l| l.split('') }
      .map { |l| l.map(&:to_i)}
  end
end
