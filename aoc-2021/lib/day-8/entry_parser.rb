require_relative 'Entry'

class EntryParser
    def parse_input(input)
        split_lines(input)
            .map { |line| parse_entry(line) }
    end

    private
    
    def split_lines(input)
        input
            .split(/\n+/)
            .reject(&:empty?)
    end

    def parse_entry(line)
        parts = line.split('|')

        patterns = parse_part(parts[0])
        outputs = parse_part(parts[1])

        Entry.new(patterns, outputs)
    end

    def parse_part(txt)
        txt
            .strip
            .split(' ')
    end

end
