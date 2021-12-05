require_relative 'segment'
require_relative 'vent_point'

class SegmentInputReader

    def read_segments(input)
        input
            .split(/\n+/)
            .reject(&:empty?)
            .map { |line| parse_segment(line) }
            .select(&:valid?)
    end

    private
    
    def parse_segment(line)
        points = line
            .split('->')
            .map(&:strip)
            .map { |values| parse_point(values) }

        Segment.new(points[0], points[1])
    end

    def parse_point(values)
        numbers = values
            .split(',')
            .map(&:to_i)

        VentPoint.new(numbers[0], numbers[1])
    end

end
