NUMBER_OF_BASINS = 3
HEIGHT_THRESHOLD = 9

class Heightmap
  attr_reader :map

  def initialize(map)
    @map = map

    @x_size = map[0].size
    @y_size = map.size
  end

  def sum_risk_levels
    get_risk_zones
      .map { |x, y| get_risk_level(x, y) }
      .sum
  end

  def multiple_largest_basins
    largest_basins = Array.new(NUMBER_OF_BASINS, 0)

    get_risk_zones.each do |x, y|
      size = get_basin_size(x, y)

      if size > largest_basins.last
        largest_basins[NUMBER_OF_BASINS - 1] = size
        largest_basins.sort!.reverse!
      end
    end

    largest_basins.reduce(:*)
  end

  private

  def get_risk_zones
    risk_zones = []

    (0..@y_size - 1).each do |y|
      (0..@x_size - 1).each do |x|
        risk_zones << [x, y] if is_risk_zone?(x, y)
      end
    end

    risk_zones
  end

  def is_risk_zone?(x, y)
    value = get_value(x, y)

    get_surrounding_points(x, y)
      .map { |px, py| get_value(px, py) }
      .all? { |surrounding_value| lesser?(value, surrounding_value) }
  end

  def get_surrounding_points(x, y)
    up = y > 0 ? [x, y - 1] : nil
    down = y < @y_size - 1 ? [x, y + 1] : nil

    left = x > 0 ? [x - 1, y] : nil
    right = x < @x_size - 1 ? [x + 1, y] : nil

    [up, down, left, right].reject(&:nil?)
  end

  def get_value(x, y)
    @map[y][x]
  end

  def lesser?(elem, other)
    other.nil? || elem < other
  end

  def get_risk_level(x, y)
    get_value(x, y) + 1
  end

  def get_basin_size(x, y)
    get_adjacent_points(x, y)
      .uniq
      .size
  end

  def get_adjacent_points(x, y)
    points = [[x, y]]
    value = get_value(x, y)

    get_surrounding_points(x, y)
      .select do |px, py|
      surrounding_value = get_value(px, py)
      surrounding_value > value && surrounding_value < HEIGHT_THRESHOLD
    end
      .each { |px, py| points += get_adjacent_points(px, py) }

    points
  end
end
