Feature: pet operations

    Scenario: client wants to get all Pets I have
      When the client calls /pet/getAll
      Then the client get all pets

    Scenario: client wants to get the status from a pet
      When the client calls /pet/getPetStatus
      Then the client get the status

    Scenario: client wants to give me a pet
      When the client calls addPet
      Then the client give me a pet





