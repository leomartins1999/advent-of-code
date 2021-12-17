require_relative 'cave_traverser'

# Solution for day 15 of Advent of Code 2021
class Day15
  def solve_first(input)
    map = read_input(input)

    @traverser = CaveTraverser.new(map)

    @traverser.get_best_risk
  end

  def solve_second(input)
    map = read_input(input)

    big_map = bigiffy_map(map)

    @traverser = CaveTraverser.new(big_map)

    @traverser.get_best_risk
  end

  private

  def read_input(input)
    input
      .split(/\n+/)
      .reject(&:empty?)
      .map { |l| l.split('') }
      .map { |l| l.map(&:to_i) }
  end

  # make it a big fucking map
  def bigiffy_map(map, multiplier = 5)
    increased_rows_map = increase_rows_for_map(map, multiplier)

    increase_columns_for_map(increased_rows_map, multiplier)
  end

  def increase_rows_for_map(map, multiplier)
    res = [map]

    (1..multiplier - 1).each do |_|
      res = res << res.last.map { |line| line.map(&:next).map { |elem| elem == 10 ? 1 : elem } }
    end

    res.flatten(1)
  end

  def increase_columns_for_map(map, multiplier)
    map.map do |line|
      res = [line]

      (1..multiplier - 1).each do |_|
        res = res << res.last.map(&:next).map { |elem| elem == 10 ? 1 : elem }
      end

      res.flatten(1)
    end
  end
end
