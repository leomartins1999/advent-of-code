require_relative 'heightmap'

class HeighmapParser
  def parse(input)
    map = split_lines(input).map { |line| to_risk_array(line) }

    Heightmap.new(map)
  end

  private

  def split_lines(lines)
    lines
      .split(/\n+/)
      .reject(&:empty?)
  end

  def to_risk_array(line)
    line
      .split('')
      .map(&:to_i)
  end
end
