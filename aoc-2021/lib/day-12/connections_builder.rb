class ConnectionsBuilder

    def build(input)
      connections = parse_input(input)

      connections.reduce({}) { |map, con| add_connection(map, con) }
    end

    private

    def parse_input(input)
      input
        .split(/\n+/)
        .reject(&:empty?)
        .map(&:strip)
        .map { |line| line.split('-') }
        .map { |parts| [parts[0], parts[1]] }
    end

    def add_connection(map, con)
      from, to = con

      connect(map, from, to)
      connect(map, to, from)

      map
    end

    def connect(map, from, to)
      if map.include?(from)
        map[from] = map[from] << to
      else
        map[from] = [to]
      end
    end

end
