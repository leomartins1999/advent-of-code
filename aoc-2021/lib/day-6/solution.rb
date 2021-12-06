require_relative 'fish_herd'

DEFAULT_DAYS = 80.freeze

# Solution for day 6 of Advent of Code 2021
class Day6

  def solve_first(input, nr_days = DEFAULT_DAYS)
    fish = read_input(input)

    fish_herd = FishHerd.new(fish)

    fish_herd.simulate(nr_days)

    fish_herd.number_of_fish
  end

  def solve_second(input)
    solve_first(input, 256)
  end

  private

  def read_input(input)
    input
      .split(',')
      .reject(&:empty?)
      .map(&:to_i)
  end
end
