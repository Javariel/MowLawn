# read file
from multiprocessing.pool import ThreadPool

from mow_lawn_funcs import init_matrix_lawn, init_mowers, move_mower

with open("./data/input/example.txt", "r") as f:
    file_content = [c.split(" ") for c in f.read().splitlines()]

    lawn_size = [int(v) + 1 for v in file_content[0]]
    mowers_positions = [v for i, v in enumerate(file_content) if i % 2 == 1]
    mowers_instructions = [v for i, v in enumerate(file_content[1:]) if i % 2 == 1]

    matrix = init_matrix_lawn(lawn_size[0], lawn_size[1], mowers_positions)
    mower_list = init_mowers(mowers_positions)

    pool = ThreadPool(8)

    pool.map(
        lambda i: move_mower(mowers_instructions[i][0], mower_list[i], matrix),
        range(len(mower_list))
    )

print(matrix)
