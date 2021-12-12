class CaveGraph

    def initialize(connections)
      @connections = connections
    end

    def get_paths(can_repeat_downcase_cave = false)
      traverse(can_repeat_downcase_cave: can_repeat_downcase_cave)
    end

    private

    def traverse(
      curr: 'start',
      traverse: [],
      result: [],
      can_repeat_downcase_cave: false,
      already_repeated_cave: false
    )
      if curr == 'end'
        result << traverse
        return result
      end

      @connections[curr]
        .reject { |opt| opt == 'start' }
        .each do |v|
          if can_traverse?(v, traverse)
            traverse(curr: v, traverse: dup_and_add(traverse, v), result: result, can_repeat_downcase_cave: can_repeat_downcase_cave, already_repeated_cave: already_repeated_cave)
          elsif can_repeat_traverse?(can_repeat_downcase_cave, already_repeated_cave)
            traverse(curr: v, traverse: dup_and_add(traverse, v), result: result, can_repeat_downcase_cave: can_repeat_downcase_cave, already_repeated_cave: true)
          end
        end

      result
    end

    def can_traverse?(v, traverse)
      v == v.upcase || !traverse.include?(v)
    end

    def can_repeat_traverse?(can_repeat_downcase_cave, already_repeated_cave)
      can_repeat_downcase_cave && !already_repeated_cave
    end

    def dup_and_add(arr, v)
      arr.dup << v
    end

end
