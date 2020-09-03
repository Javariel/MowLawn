

orientatio_rules = {
        'L': {
            'N': 'W',
            'S': 'E',
            'W': 'S',
            'E': 'N'
        },
        'R': {
            'N': 'E',
            'S': 'W',
            'W': 'N',
            'E': 'S'
        },
    }


class Mower:
    def __init__(self, id, x, y, orientation):
        self.id = id
        self.x = int(x)
        self.y = int(y)
        self.orientation = orientation

    def move(self, action, matrix_lawn):
        tmp_x, tmp_y = self.x, self.y
        if action == 'F':
            if self.orientation == 'N':
                tmp_y += 1
            elif self.orientation == 'S':
                tmp_y -= 1
            elif self.orientation == 'E':
                tmp_x += 1
            elif self.orientation == 'W':
                tmp_x -= 1
            # check with matrix lawn
            if tmp_x < len(matrix_lawn[0]) and tmp_y < len(matrix_lawn) and matrix_lawn[tmp_y][tmp_x] == 0:
                matrix_lawn[self.y][self.x] = 0
                self.x = tmp_x
                self.y = tmp_y
                matrix_lawn[tmp_y][tmp_x] = 1
                print("Mower ", self.id," moves to: (", tmp_x, ", ", tmp_y, ", ", self.orientation, ")")
            else:
                print("Ignore move")
        else:
            self.orientation = orientatio_rules[action][self.orientation]

