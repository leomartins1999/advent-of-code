require_relative 'segment_input_reader'
require_relative 'vent_map'

# Solution for day 1 of Advent of Code 2021
class Day5
  
  def initialize
    @input_reader = SegmentInputReader.new
  end

  def solve_first(input)
    run(input, false)
  end

  def solve_second(input)
    run(input, true)
  end

  def run(input, diagonal_enabled)
    segments = @input_reader.read_segments(input)
    map_size = get_map_size(segments)

    map = VentMap.new(map_size, diagonal_enabled)

    map_segments(map, segments)

    map.number_of_danger_areas
  end

  private

  def get_map_size(segments)
    segments
      .map { |seg| [seg.start.x, seg.start.y, seg.end.x, seg.end.y] }
      .flatten
      .max
      .next
  end

  def map_segments(map, segments)
    segments.each do |segment|
      map.add_segment(segment)
    end
  end
end
