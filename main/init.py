import request.request_service as requestService


def main():
    print("Data:",
          requestService.get("https://jsonplaceholder.typicode.com/todos/1"))


main()
