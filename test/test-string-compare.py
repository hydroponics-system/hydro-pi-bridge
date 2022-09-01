import unittest


class TestStringCompare(unittest.TestCase):

    def test_string_compare(self):
        self.assertEqual("hello", "hello")


if __name__ == '__main__':
    unittest.main()
