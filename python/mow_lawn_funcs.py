from mower import Mower


def init_matrix_lawn(n, m, list_position):
    matrix_lawn = [[0 for col in range(n)] for row in range(m)]
    for x, y, _ in list_position:
        print(x, y)
        matrix_lawn[int(y)][int(x)] = 1
    return matrix_lawn


def init_mowers(input_data):
    list_mower = list()
    for id, v in enumerate(input_data):
        x, y, orientation = v
        mower = Mower(id, x, y, orientation)
        list_mower.append(mower)

    return list_mower


def move_mower(actions, mower, matrix_lawn):
    for action in actions:
        print("Next Action:", action)
        mower.move(action, matrix_lawn)


if __name__ == 'main':
    print(init_matrix_lawn(3, 5, []))


