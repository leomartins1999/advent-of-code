require_relative 'points_folder'

# Solution for day 13 of Advent of Code 2021
class Day13
  def initialize
    @folder = PointsFolder.new
  end

  def solve_first(input)
    points, lines = parse_input(input)

    folded = @folder.fold(points, lines.first)

    folded.size
  end

  def solve_second(input)
    points, lines = parse_input(input)

    folded = lines.reduce(points) { |acc, line| @folder.fold(acc, line) }

    print_number(folded)

    nil
  end

  private

  def parse_input(input)
    lines = input.split(/\n+/).reject(&:empty?)

    [parse_points(lines), parse_folds(lines)]
  end

  def parse_points(lines)
    lines
      .select { |l| l.include?(',') }
      .map { |l| l.split(',') }
      .map { |x, y| [x.to_i, y.to_i] }
  end

  def parse_folds(lines)
    lines
      .reject { |l| l.include?(',') }
      .map { |l| l.split(' ').last }
      .map { |l| l.split('=') }
      .map { |type, coord| [type, coord.to_i] }
  end

  def print_number(points)
    max_x = points.map { |x, _y| x }.max
    max_y = points.map { |_x, y| y }.max

    puts ''
    (0..max_y).each do |y|
      puts (0..max_x).map { |x| get_position_char(points, x, y) }.join('')
    end
  end

  def get_position_char(points, x, y)
    points.any? { |px, py| px == x && py == y } ? '#' : '.'
  end
end
